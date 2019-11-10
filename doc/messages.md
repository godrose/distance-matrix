## Algorithm

**Given** 
There are 3 sources of messages. The messages arrive via these sources asynchoronously and the ultimatel data source should satify the following conditions:
- There should be no duplicate messages (messages are identified by text)
- There should be a certain priority depending on the source
- There should be a probation period for low-priority messages before they are accepted (i.e. if a message with the same id arrives during this period the low-priority message is discarded)

**Data structures**
Use two data structures: one is for storing accepted messages and displaying them to the consuming user interface and the other for the intermediate storage before promotion.
The first data source should be some kind of observable map where the **id** serves as the key. Let's refer to it as *accepted*
The second data source should be some kind of map as well; the map is populated with messages that can't be promoted immediatel. Let's refer to it as *intermediate*

Use the following data structure for the original message:
*id* - message id (text)
*source* - message source

Use the following data structure for the accepted message:
*id* - message id (text)
*text* - message text

Use the following data structure for the intermediate message:
*id* - message id (text)
*source* - message source
*dueTo* - the point of time when the probation period elapses. implemented with an internal timer.

**Proposal**
Use the producer-observer approach where the messages are produced and pushed to the certain data source *intermediate* and then observed until they are finalized.

**Produce Message Flow**

1. *Message* arrives
2. Check the *message id* against the *accepted*. If it's present discard the *message* and continue to the next one. If it's absent proceed to the **Step 3** (Time Complexity - O(1))
3. Check the *message source*. If it's top priority immediately push the message to the *accepted* and continue to the next one. If it's not proceed to the **Step 4** (Time Complexity - O(1))
4. Check the *message id* against the *intermediate*. If it's present check the *message source*. If its priority is higher remove the existing message and continue to the **Step 5**. If it's not discard the *message*. If the *message* is absent continue to the **Step 5**. (Time Complexity - O(1))
5. Update the *message dueTo* with the sum of *current time* and the *probation period duration* meaning the message is enriched with an internal timer with the *probation period duration* as its period. Push the message to the **intermediate**, subscribe to its notifications and start the timer. (Time Complexity - O(1))

**Observe Message Flow**

The *subscriptions* are stored in the internal map where the *id* serves as the key.

On each notification do the following
1. Promote the *message* to the *accepted*. (Time Complexity - O(1))
2. Cancel the subscription - Remove it from the *subcsriptions* and unsubscribe. (Time Complexity - O(1))
3. Finalize the *message* - Remove it from the *intermediate* and free the resources. (Time Complexity - O(1))

With this approach we ensure reactive way of handling *probation period* expiration saving needless time checks and achieving better time complexity result.