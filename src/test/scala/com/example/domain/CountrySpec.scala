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

class CountrySpec extends AnyWordSpec with Matchers {
  "The Country" should {
    "have example test that can be removed" in {
      val testKit = CountryTestKit(new Country(_))
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

    "correctly process commands of type CreateCountry" in {
      val testKit = CountryTestKit(new Country(_))
      // val result: EventSourcedResult[Empty] = testKit.createCountry(example.AddNewCountry(...))
    }

    "correctly process commands of type ImportCountry" in {
      val testKit = CountryTestKit(new Country(_))
      // val result: EventSourcedResult[Empty] = testKit.importCountry(example.AddNewCountry(...))
    }

    "correctly process commands of type GetCountry" in {
      val testKit = CountryTestKit(new Country(_))
      // val result: EventSourcedResult[example.Country] = testKit.getCountry(example.CountryIsoCode(...))
    }
  }
}
