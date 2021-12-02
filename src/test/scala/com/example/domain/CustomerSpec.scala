package com.example.domain

import com.akkaserverless.scalasdk.eventsourcedentity.EventSourcedEntity
import com.akkaserverless.scalasdk.testkit.EventSourcedResult
import com.example
import com.google.protobuf.empty.Empty
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class CustomerSpec extends AnyWordSpec with Matchers {
  "The Customer" should {
    "have example test that can be removed" in {
      val testKit = CustomerTestKit(new Customer(_))
      // use the testkit to execute a command:
      // val result: EventSourcedResult[R] = testKit.someOperation(SomeRequest("id"));
      // verify the emitted events
      // val actualEvent: ExpectedEvent = result.nextEventOfType[ExpectedEvent]
      // actualEvent shouldBe expectedEvent
      // verify the final state after applying the events
      // testKit.state() shouldBe expectedState
      // verify the response
      // result.reply shouldBe expectedReply
      // verify the final state after the command
    }

    "correctly process commands of type CreateCustomer" in {
      val testKit = CustomerTestKit(new Customer(_))
      // val result: EventSourcedResult[Empty] = testKit.createCustomer(example.AddNewCustomer(...))
    }

    "correctly process commands of type ImportCustomer" in {
      val testKit = CustomerTestKit(new Customer(_))
      // val result: EventSourcedResult[Empty] = testKit.importCustomer(example.AddNewCustomer(...))
    }

    "correctly process commands of type RenameCustomer" in {
      val testKit = CustomerTestKit(new Customer(_))
      // val result: EventSourcedResult[Empty] = testKit.renameCustomer(example.CustomerNewName(...))
    }

    "correctly process commands of type ChangeCustomerCountry" in {
      val testKit = CustomerTestKit(new Customer(_))
      // val result: EventSourcedResult[Empty] = testKit.changeCustomerCountry(example.CustomerNewCountry(...))
    }

    "correctly process commands of type GetCustomer" in {
      val testKit = CustomerTestKit(new Customer(_))
      // val result: EventSourcedResult[example.Customer] = testKit.getCustomer(example.CustomerTrigram(...))
    }

    "correctly process commands of type DeleteCustomer" in {
      val testKit = CustomerTestKit(new Customer(_))
      // val result: EventSourcedResult[Empty] = testKit.deleteCustomer(example.DeleteExistingCustomer(...))
    }
  }
}
