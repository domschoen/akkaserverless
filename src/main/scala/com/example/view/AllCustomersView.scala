package com.example.view

import com.akkaserverless.scalasdk.view.View.UpdateEffect
import com.akkaserverless.scalasdk.view.ViewContext
import com.google.protobuf.empty.Empty
import com.example.domain.CustomerCreated
import com.example.domain.CustomerDeleted
import com.example.domain.CustomerHeadCountryChanged
import com.example.domain.CustomerImported
import com.example.domain.CustomerRenamed
import com.example.domain.CustomerState

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class AllCustomersView(context: ViewContext) extends AbstractAllCustomersView {

  override def emptyState: CustomerState = CustomerState.defaultInstance

  override def processCustomerCreated(
    state: CustomerState, customerCreated: CustomerCreated): UpdateEffect[CustomerState] =
      if (state != emptyState) effects.ignore() // already  created
      else effects.updateState(customerCreated.getCustomer)

  override def processCustomerImported(
    state: CustomerState, customerImported: CustomerImported): UpdateEffect[CustomerState] =
      if (state != emptyState) effects.ignore() // already  created
      else effects.updateState(customerImported.getCustomer)

  override def processCustomerRenamed(
    state: CustomerState, customerRenamed: CustomerRenamed): UpdateEffect[CustomerState] =
      if (state == emptyState) effects.ignore() // Cannot rename if not exits
      else effects.updateState(state.copy(name = customerRenamed.newName))

  override def processCustomerHeadCountryChanged(
    state: CustomerState, customerHeadCountryChanged: CustomerHeadCountryChanged): UpdateEffect[CustomerState] =
      if (state == emptyState) effects.ignore() // Cannot change country if not exits
      else effects.updateState(state.copy(headCountry = customerHeadCountryChanged.newHeadCountry))

  override def processCustomerDeleted(
    state: CustomerState, customerDeleted: CustomerDeleted): UpdateEffect[CustomerState] =
      if (state == emptyState) effects.ignore() // Cannot delete if not exits
      else effects.updateState(CustomerState.defaultInstance)
}
