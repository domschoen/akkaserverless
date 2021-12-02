package com.example.domain

import com.akkaserverless.scalasdk.eventsourcedentity.EventSourcedEntity
import com.akkaserverless.scalasdk.eventsourcedentity.EventSourcedEntityContext
import com.google.protobuf.empty.Empty
import com.example
import com.example.AddNewCountry

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

/** An event sourced entity. */
class Country(context: EventSourcedEntityContext) extends AbstractCountry {
  override def emptyState: CountryState = CountryState.defaultInstance

  override def createCountry(currentState: CountryState, addNewCountry: example.AddNewCountry): EventSourcedEntity.Effect[Empty] =
    if (!currentState.equals(CountryState.defaultInstance)) {
      effects.error("Country already exists");
    } else {
      val countryState = convert(addNewCountry);

      val event = CountryCreated(
          country = Some(countryState),
          byUserID = addNewCountry.userID,
          date = System.currentTimeMillis()
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def importCountry(currentState: CountryState, addNewCountry: example.AddNewCountry): EventSourcedEntity.Effect[Empty] =
    if (!currentState.equals(CountryState.defaultInstance)) {
      effects.error("Country already exists");
    } else {
      val countryState = convert(addNewCountry);

      val event = CountryImported(
        country = Some(countryState),
        byUserID = addNewCountry.userID,
        date = System.currentTimeMillis()
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def getCountry(currentState: CountryState, countryIsoCode: example.CountryIsoCode): EventSourcedEntity.Effect[example.Country] =
    effects.reply(convert(currentState))

  override def countryCreated(currentState: CountryState, countryCreated: CountryCreated): CountryState =
    countryCreated.getCountry

  override def countryImported(currentState: CountryState, countryImported: CountryImported): CountryState =
    countryImported.getCountry


  private def convert(countryState: CountryState) : com.example.Country =
    com.example.Country(
      isoCode = countryState.isoCode,
      name = countryState.name
    )

  private def convert(addNewCountry: AddNewCountry): CountryState =
    CountryState(
      isoCode = addNewCountry.isoCode,
      name = addNewCountry.name
    )


}
