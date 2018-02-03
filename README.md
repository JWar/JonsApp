# JonsApp
An attempt at reverse engineering WhatsApp (sort of!).

This is obviously an ongoing project.

It is really basic and mostly involves two lists. One to show the list of current conversations and one to show the messages within those conversations.
I mean lets face it thats really all messaging apps are... with some bells and whistles tagged on. Of course the interesting thing is the behind the scenes
stuff that is needed to make it work.

I am trying to demonstrate a number of techniques including the MVP design pattern and usage of RxJava.

There is a back end part but this uses software that can't be public - treat the back end as a black box that simply receives a new message 
(in the form of a transaction (Txn)) and sends it via Firebase to the recipient/s. Of course most of the interesting logic goes on there... but thats by 
the by.

Of course the back end doesnt work like WhatsApp, they use their own system (presumably sockets...) and they also have encryption etc...
but the principles should be similar and JonsApp shou ld be written in a way that allows easy adding of encryption, this presumably would just make use of an 'encryption
object' that does its thing on any new messages received or sent?

Notes/Things to look out for:

Using MVP seems more verbose and often requires more code/classes than using other techniques. The benefits however to using this pattern
are seen when testing and when taking into account structure. Verbosity might be intimidating but it can also be useful for clarity and help immensely
when coming back to a project or when working in a team. After all, bugs are a huge time sink when it comes to programming!

Now the main benefit of using this design pattern is that testing becomes easier. Think of this as trying to avoid using Android Frameworks
as much as possible. Basically you want to avoid putting logic in fragments/activitys/services. Everything should be abstracted as much as possible into
custom made classes/interfaces/blahs. This is so you can simply take each and every classes and test it without having to faff about with Android
Frameworks. This also makes 'Injection' an important concept (see Dagger for a more formal way. I simply use static methods injected into the constructor), and it is 
important to understand why a class's constructor requires all the dependencies in it's parameters (so they can be mocked. If you instantiate objects within an object you 
run into problems when testing - this is to ensure you are testing just that class). 

Using Contracts (in the form of interfaces) is helpful in imposing structure on the system. This may seem like more 
work and it is more code, but setting up a contract makes generating your classes easier. If you spend the time in the contract creation phase to make sure you have
what you need, where you need it, then it becomes easier to implement it. It also means you can very easily add all the methods (use Ctrl+I), thus allowing you to get 
onto the business of adding the logic quicker. It took me a while to appreciate Contracts, now I often just sit with the contract class open whilst thinking about 
how to write the code, once I have it set in my mind I create the methods in the contract and go from there.

Starting with RxJava is intimidating. I'm not going to give a tutorial as there are plenty available, I will just say a couple of things I experienced when using/learning it.
I found understanding Consumers and Functions harder than I thought I would. Basically they act upon whatever is being 'done'... they 'Consume' or apply a 'Function' on 
whatever is being Observed (the Observable). Using them is fairly simple, just type 'new' followed by Consumer or Function into the parameter slot in the method 
(usually .subscribe), Studio's code creation handles the rest. I know this seems like an obvious thing but I wasted longer than I care to admit on that... In the Consumer 
'accept' is simply the method that has the result in its paramter, nothing more, this allows you to do whatever you want with the object, think of it as the 'returned' object.   

TDs:
Server-side:
    Need to think about Msgs sending and updating sent/delivered status. Should the Txn response provide this data or should
    it require a Firebase notification sent back to the phone to update when delivered. This will probably be the most flexible
    given that cant guarantee WHEN the Firebase Msg is sent.
    Need to think about TelTo! How should server side handle the multiple Tels...?

Phone-side:    
    Finish UI. Need to check whats been done.
    Get basic new conversation/new contact handling sorted.    

