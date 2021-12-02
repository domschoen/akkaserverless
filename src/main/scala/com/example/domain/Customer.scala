package com.example.domain

import com.akkaserverless.scalasdk.eventsourcedentity.EventSourcedEntity
import com.akkaserverless.scalasdk.eventsourcedentity.EventSourcedEntityContext
import com.google.protobuf.empty.Empty
import com.example

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

/** An event sourced entity. */
class Customer(context: EventSourcedEntityContext) extends AbstractCustomer {
  override def emptyState: CustomerState = CustomerState.defaultInstance

  override def createCustomer(currentState: CustomerState, addNewCustomer: example.AddNewCustomer): EventSourcedEntity.Effect[Empty] =
    if (!currentState.equals(CustomerState.defaultInstance)) {
      effects.error("Customer already exists");
    } else {
      val customerState = convert(addNewCustomer);

      val event = CustomerCreated(
        customer = Some(customerState),
        byUserID = addNewCustomer.userID,
        date = System.currentTimeMillis()
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def importCustomer(currentState: CustomerState, addNewCustomer: example.AddNewCustomer): EventSourcedEntity.Effect[Empty] =
    if (!currentState.equals(CustomerState.defaultInstance)) {
      effects.error("Customer already exists");
    } else {
      val customerState = convert(addNewCustomer);

      val event = CustomerImported(
        customer = Some(customerState),
        byUserID = addNewCustomer.userID,
        date = System.currentTimeMillis()
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def renameCustomer(currentState: CustomerState, customerNewName: example.CustomerNewName): EventSourcedEntity.Effect[Empty] =
    if (currentState.equals(CustomerState.defaultInstance)) {
      effects.error("Customer must be created before name can be changed.");
    } else {
      val event = CustomerRenamed(
        newName = customerNewName.name,
        byUserID = customerNewName.userID,
        date = System.currentTimeMillis()
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def changeCustomerCountry(currentState: CustomerState, customerNewCountry: example.CustomerNewCountry): EventSourcedEntity.Effect[Empty] =
    if (currentState.equals(CustomerState.defaultInstance)) {
      effects.error("Customer must be created before head country can be changed.");
    } else {
      val event = CustomerHeadCountryChanged(
        newHeadCountry = customerNewCountry.headCountry,
        byUserID = customerNewCountry.userID,
        date = System.currentTimeMillis()
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def getCustomer(currentState: CustomerState, customerTrigram: example.CustomerTrigram): EventSourcedEntity.Effect[example.Customer] =
    effects.reply(convert(currentState))

  override def deleteCustomer(currentState: CustomerState, deleteExistingCustomer: example.DeleteExistingCustomer): EventSourcedEntity.Effect[Empty] =
    if (currentState.equals(CustomerState.defaultInstance)) {
      effects.error("Customer does not exists.");
    } else {
      val event = CustomerDeleted(
        byUserID = deleteExistingCustomer.userID,
        date = System.currentTimeMillis()
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def customerCreated(currentState: CustomerState, customerCreated: CustomerCreated): CustomerState =
    customerCreated.getCustomer

  override def customerImported(currentState: CustomerState, customerImported: CustomerImported): CustomerState =
    customerImported.getCustomer

  override def customerRenamed(currentState: CustomerState, customerRenamed: CustomerRenamed): CustomerState =
    currentState.copy(name = customerRenamed.newName)

  override def customerHeadCountryChanged(currentState: CustomerState, customerHeadCountryChanged: CustomerHeadCountryChanged): CustomerState =
    currentState.copy(headCountry = customerHeadCountryChanged.newHeadCountry)

  override def customerDeleted(currentState: CustomerState, customerDeleted: CustomerDeleted): CustomerState =
    CustomerState.defaultInstance


  private def convert(customerState: CustomerState) : com.example.Customer =
    com.example.Customer(
      trigram = customerState.trigram,
      customerID = customerState.customerID,
      name = customerState.name,
      customerType = customerState.customerType,
      dynamicsAccountID = customerState.dynamicsAccountID,
      headCountry = customerState.headCountry,
      region = customerState.region
    )

  private def convert(addNewCustomer: example.AddNewCustomer): CustomerState =
    CustomerState(
      trigram = addNewCustomer.trigram,
      customerID = addNewCustomer.customerID,
      name = addNewCustomer.name,
      customerType = addNewCustomer.customerType,
      dynamicsAccountID = addNewCustomer.dynamicsAccountID,
      headCountry = addNewCustomer.headCountry,
      region = addNewCustomer.region
    )

}
