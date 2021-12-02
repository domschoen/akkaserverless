package com.example.actions

import com.akkaserverless.scalasdk.action.Action
import com.akkaserverless.scalasdk.action.ActionCreationContext
import com.example.domain.CustomerCreated
import com.example.domain.CustomerHeadCountryChanged
import com.example.domain.CustomerImported
import com.example.domain.CustomerRenamed

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

/** An action. */
class CustomerJournalToTopicAction(creationContext: ActionCreationContext) extends AbstractCustomerJournalToTopicAction {

  /** Handler for "CreateCustomer". */
  override def createCustomer(customerCreated: CustomerCreated): Action.Effect[CustomerCreated] =
    effects.reply(customerCreated)

  /** Handler for "RenameCustomer". */
  override def renameCustomer(customerRenamed: CustomerRenamed): Action.Effect[CustomerRenamed] =
    effects.reply(customerRenamed)

  /** Handler for "ImportCustomer". */
  override def importCustomer(customerImported: CustomerImported): Action.Effect[CustomerImported] =
    effects.reply(customerImported)

  /** Handler for "ChangeCustomerHeadCountry". */
  override def changeCustomerHeadCountry(customerHeadCountryChanged: CustomerHeadCountryChanged): Action.Effect[CustomerHeadCountryChanged] =
    effects.reply(customerHeadCountryChanged)
}

