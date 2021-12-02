package com.example.actions

import com.akkaserverless.scalasdk.action.Action
import com.akkaserverless.scalasdk.testkit.ActionResult
import com.example.domain.CustomerCreated
import com.example.domain.CustomerHeadCountryChanged
import com.example.domain.CustomerImported
import com.example.domain.CustomerRenamed
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class CustomerJournalToTopicActionSpec
    extends AnyWordSpec
    with Matchers {

  "CustomerJournalToTopicAction" must {

    "have example test that can be removed" in {
      val testKit = CustomerJournalToTopicActionTestKit(new CustomerJournalToTopicAction(_))
      // use the testkit to execute a command
      // and verify final updated state:
      // val result = testKit.someOperation(SomeRequest)
      // verify the response
      // result.reply shouldBe expectedReply
    }

    "handle command CreateCustomer" in {
      val testKit = CustomerJournalToTopicActionTestKit(new CustomerJournalToTopicAction(_))
      // val result = testKit.createCustomer(CustomerCreated(...))
    }

    "handle command RenameCustomer" in {
      val testKit = CustomerJournalToTopicActionTestKit(new CustomerJournalToTopicAction(_))
      // val result = testKit.renameCustomer(CustomerRenamed(...))
    }

    "handle command ImportCustomer" in {
      val testKit = CustomerJournalToTopicActionTestKit(new CustomerJournalToTopicAction(_))
      // val result = testKit.importCustomer(CustomerImported(...))
    }

    "handle command ChangeCustomerHeadCountry" in {
      val testKit = CustomerJournalToTopicActionTestKit(new CustomerJournalToTopicAction(_))
      // val result = testKit.changeCustomerHeadCountry(CustomerHeadCountryChanged(...))
    }

  }
}
