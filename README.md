# JonsApp
An attempt at reverse engineering WhatsApp

This is obviously an ongoing project.

It is really basic and mostly involves two lists. One to show the list of current conversations and one to show the messages within those conversations.
I mean lets face it thats really all messaging apps are... with some bells and whistles tagged on.

I am trying to demonstrate a number of techniques including the MVP design pattern and usage of RxJava.

There is a back end element but this uses software that can't be public - Treat the back end as a black box that simply receives a new message 
(in the form of a transaction (Txn)) and sends it via Firebase to the recipient/s. 

Of course the back end doesnt work like WhatsApp, they use their own system (presumably sockets...) and they also have encryption etc...
but the principles should be similar and JonsApp should be written in a way that allows easy adding of encryption... presumably just an encryption
'object' that does its thing on any new messages received or sent?

TDs:
Server-side:
    Need to think about Msgs sending and updating sent/delivered status. Should the Txn response provide this data or should
    it require a Firebase notification sent back to the phone to update when delivered. This will probably be the most flexible
    given that cant guarantee WHEN the Firebase Msg is sent.
    Need to think about TelTo! How should server side handle the multiple Tels...?

Phone-side:
    Finish database set up (schema, dbhelper etc...).
    Finish UI. Need to check whats been done.
    Get basic new conversation/new contact handling sorted.
    Lets get some dummy data in the system too...

