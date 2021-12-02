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

class UserSpec extends AnyWordSpec with Matchers {
  "The User" should {
    "have example test that can be removed" in {
      val testKit = UserTestKit(new User(_))
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

    "correctly process commands of type CreateAdminUser" in {
      val testKit = UserTestKit(new User(_))
      // val result: EventSourcedResult[Empty] = testKit.createAdminUser(example.AddAdminUser(...))
    }

    "correctly process commands of type CreateUser" in {
      val testKit = UserTestKit(new User(_))
      // val result: EventSourcedResult[Empty] = testKit.createUser(example.AddNewUser(...))
    }

    "correctly process commands of type GetUser" in {
      val testKit = UserTestKit(new User(_))
      // val result: EventSourcedResult[example.CurrentUser] = testKit.getUser(example.UserID(...))
    }

    "correctly process commands of type UpdateIsAdmin" in {
      val testKit = UserTestKit(new User(_))
      // val result: EventSourcedResult[Empty] = testKit.updateIsAdmin(example.SetIsAdmin(...))
    }
  }
}
