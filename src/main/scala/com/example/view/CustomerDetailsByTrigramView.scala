package com.example.view

import com.akkaserverless.scalasdk.view.View.UpdateEffect
import com.akkaserverless.scalasdk.view.ViewContext
import com.fasterxml.jackson.databind.ObjectMapper
import com.example.domain.{CustomerCreated, CustomerDeleted, CustomerHeadCountryChanged, CustomerImported, CustomerRenamed, CustomerResponse, CustomerState}
import com.example.domain.CustomerEvent
import io.circe._
import io.circe.generic.auto._
import io.circe.derivation.annotations.JsonCodec
import io.circe.syntax._
import io.circe.generic.semiauto._
import io.circe.generic.semiauto.deriveDecoder

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.



class CustomerDetailsByTrigramView(context: ViewContext) extends AbstractCustomerDetailsByTrigramView {

  override def emptyState: CustomerResponse = CustomerResponse.defaultInstance


  @JsonCodec
  case class CustomerStateCodec (
    trigram: String,
    customerID: Long,
    name : String,
    customerType : String,
    dynamicsAccountID : String,
    headCountry : String,
    region : String
                                )

  @JsonCodec
  case class CustomerCreatedCodec (
    customer: CustomerStateCodec,
    date: Long,
    byUserID: String
  )

  @JsonCodec
  case class CustomerRenamedCodec (
                                    newName: String,
                                    date: Long,
                                    byUserID: String
                                  )
  @JsonCodec
  case class CustomerHeadCountryChangedCodec (
                                               newHeadCountry: String,
                                    date: Long,
                                    byUserID: String
                                  )
  @JsonCodec
  case class CustomerDeletedCodec (
                                               date: Long,
                                               byUserID: String
                                             )


  override def processCustomerCreated(
    state: CustomerResponse, customerCreated: CustomerCreated): UpdateEffect[CustomerResponse] =
      if (state != emptyState) effects.ignore() // already  created
      else {
        val result = convert(customerCreated.getCustomer)
        val action = customerCreated.getClass.getName
        val eventCodec = CustomerCreatedCodec(
          CustomerStateCodec(
            trigram = customerCreated.getCustomer.trigram,
            customerID = customerCreated.getCustomer.customerID,
            name = customerCreated.getCustomer.name,
            customerType = customerCreated.getCustomer.customerType,
            dynamicsAccountID = customerCreated.getCustomer.dynamicsAccountID,
            headCountry = customerCreated.getCustomer.headCountry,
            region = customerCreated.getCustomer.region
          ),
          date = customerCreated.date,
          byUserID = customerCreated.byUserID
        )
        val json = eventCodec.asJson.noSpaces
        val event = CustomerEvent(action,json)
        val update = result.copy(events = result.events :+ event)

        effects.updateState(update)
      }

  override def processCustomerImported(
    state: CustomerResponse, customerImported: CustomerImported): UpdateEffect[CustomerResponse] =
      if (state != emptyState) effects.ignore() // already  created
      else {
        val result = convert(customerImported.getCustomer)
        val action = customerImported.getClass.getName
        val eventCodec = CustomerCreatedCodec(
          CustomerStateCodec(
            trigram = customerImported.getCustomer.trigram,
            customerID = customerImported.getCustomer.customerID,
            name = customerImported.getCustomer.name,
            customerType = customerImported.getCustomer.customerType,
            dynamicsAccountID = customerImported.getCustomer.dynamicsAccountID,
            headCountry = customerImported.getCustomer.headCountry,
            region = customerImported.getCustomer.region
          ),
          date = customerImported.date,
          byUserID = customerImported.byUserID
        )
        val json = eventCodec.asJson.noSpaces
        val event = CustomerEvent(action,json)
        val update = result.copy(events = result.events :+ event)

        effects.updateState(update)
      }

  override def processCustomerRenamed(
    state: CustomerResponse, customerRenamed: CustomerRenamed): UpdateEffect[CustomerResponse] =
      if (state == emptyState) effects.ignore() // Cannot rename if not exits
      else {
        val action = customerRenamed.getClass.getName
        val eventCodec = CustomerRenamedCodec(
          newName = customerRenamed.newName,
          date = customerRenamed.date,
          byUserID = customerRenamed.byUserID
        )
        val json = eventCodec.asJson.noSpaces
        val event = CustomerEvent(action,json)
        val update = state.copy(name = customerRenamed.newName, events = state.events :+ event)

        effects.updateState(update)
      }

  override def processCustomerHeadCountryChanged(
    state: CustomerResponse, customerHeadCountryChanged: CustomerHeadCountryChanged): UpdateEffect[CustomerResponse] =
      if (state == emptyState) effects.ignore() // Cannot change country if not exits
      else {
        val action = customerHeadCountryChanged.getClass.getName
        val eventCodec = CustomerHeadCountryChangedCodec(
          newHeadCountry = customerHeadCountryChanged.newHeadCountry,
          date = customerHeadCountryChanged.date,
          byUserID = customerHeadCountryChanged.byUserID
        )
        val json = eventCodec.asJson.noSpaces
        val event = CustomerEvent(action,json)
        val update = state.copy(
          headCountry = customerHeadCountryChanged.newHeadCountry,
          events = state.events :+ event
        )

        effects.updateState(update)
      }

  // TODO Add a flag isDeleted in CustomerResponse
  override def processCustomerDeleted(
    state: CustomerResponse, customerDeleted: CustomerDeleted): UpdateEffect[CustomerResponse] =
      if (state == emptyState) effects.ignore() // Cannot delete if not exits
      else {
        val action = customerDeleted.getClass.getName
        val eventCodec = CustomerDeletedCodec(
          date = customerDeleted.date,
          byUserID = customerDeleted.byUserID
        )
        val json = eventCodec.asJson.noSpaces
        val event = CustomerEvent(action,json)
        val update = state.copy(
          events = state.events :+ event
        )
        effects.updateState(update)
      }

  private def convert(customerState: CustomerState) =
     CustomerResponse(
      trigram = customerState.trigram,
      customerID = customerState.customerID,
      name = customerState.name,
      customerType = customerState.customerType,
      dynamicsAccountID = customerState.dynamicsAccountID,
      headCountry = customerState.headCountry,
      region = customerState.region,
     )

}
