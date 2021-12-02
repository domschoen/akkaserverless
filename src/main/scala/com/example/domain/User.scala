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
class User(context: EventSourcedEntityContext) extends AbstractUser {
  override def emptyState: UserState = UserState.defaultInstance



  override def createAdminUser(currentState: UserState, addAdminUser: example.AddAdminUser): EventSourcedEntity.Effect[Empty] =
    if (!currentState.equals(UserState.defaultInstance)) {
      effects.error("User already exists");
    } else {
      val userState = UserState(
        userID = addAdminUser.userID,
        firstName = addAdminUser.firstName,
        lastName = addAdminUser.lastName,
        email = addAdminUser.email,
        isAdmin = true
      )

      val event = UserCreated(
        user = Some(userState),
        byUserID = addAdminUser.userID,
        date = System.currentTimeMillis()
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def createUser(currentState: UserState, addNewUser: example.AddNewUser): EventSourcedEntity.Effect[Empty] =
    if (!currentState.equals(UserState.defaultInstance)) {
      effects.error("User already exists");
    } else {
      val userState = convert(addNewUser);

      val event = UserCreated(
        user = Some(userState),
        date = System.currentTimeMillis(),
        byUserID = addNewUser.userID,
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def getUser(currentState: UserState, userID: example.UserID): EventSourcedEntity.Effect[example.CurrentUser] =
    effects.reply(convert(currentState))

  override def updateIsAdmin(currentState: UserState, setIsAdmin: example.SetIsAdmin): EventSourcedEntity.Effect[Empty] =
    if (currentState.equals(UserState.defaultInstance)) {
      effects.error("User must be created before we can change the admin right");
    } else {
      val event = IsAdminUpdated(
        isAdmin = setIsAdmin.isAdmin,
        date = System.currentTimeMillis(),
        byUserID = setIsAdmin.userID,
      )
      effects.emitEvent(event)
        .thenReply(_ => Empty.defaultInstance)
    }

  override def userCreated(currentState: UserState, userCreated: UserCreated): UserState =
    throw new RuntimeException("The event handler for `UserCreated` is not implemented, yet")

  override def userImported(currentState: UserState, userImported: UserImported): UserState =
    throw new RuntimeException("The event handler for `UserImported` is not implemented, yet")

  override def isAdminUpdated(currentState: UserState, isAdminUpdated: IsAdminUpdated): UserState =
    throw new RuntimeException("The event handler for `IsAdminUpdated` is not implemented, yet")


  private def convert(userState: UserState) : com.example.CurrentUser =
    com.example.CurrentUser(
      userID = userState.userID,
      firstName = userState.firstName,
      lastName = userState.lastName,
      email = userState.email,
      isAdmin = userState.isAdmin
    )

  private def convert(addNewUser: example.AddNewUser): UserState =
    UserState(
      userID = addNewUser.userID,
      firstName = addNewUser.firstName,
      lastName = addNewUser.lastName,
      email = addNewUser.email,
      isAdmin = addNewUser.isAdmin
    )


}
