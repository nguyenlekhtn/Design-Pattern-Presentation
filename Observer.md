# Observer

## Intent

* Is a behavioral design pattern
* Let you define a subscription mechanism to notify multiple objects about any events that happen to the object they're' observing

<img src="./images/observer.png">

## Problem

Imagine that you have two type of objects: a Customer and a Store. The customer is very interested in a particular brand of a product.

<figure>
  <img src="./images/observer-comic-1-en.png">
  <figcaption>
      Visiting the store vs. sending spam
  </figcaption>
</figure>

## Solution

Add a subscription mechanism to the publisher class  
This mechanism consists of
* An array field to storing a list of references to subscriber objects
* Several public methods which allow adding subscribers to and removing them from that list

<figure>
  <img src="./images/solution1-en.png">
  <figcaption>
      Visiting the store vs. sending spam
  </figcaption>
</figure>

It’s crucial that all subscribers implement the same interface  and that the publisher communicates with them only via that interface.

## Structure
<img src="./images/obs_structure-indexed%20(1).png">

[//]: # (## Pseudocode)

[//]: # (In this example, the Observer pattern lets the text editor object notify other service objects about changes in its state)

[//]: # ()
[//]: # (<figure>)

[//]: # (  <img src="./images/obs_example.png">)

[//]: # (  <figcaption>)

[//]: # (      Notifying objects about events that happen to other objects.)

[//]: # (  </figcaption>)

[//]: # (</figure>)

[//]: # ()
[//]: # (```)

[//]: # (class EventManager is)

[//]: # (    private field listeners: hash map of event types and listeners)

[//]: # ()
[//]: # (    method subscribe&#40;eventType, listener&#41; is)

[//]: # (        listeners.add&#40;eventType, listener&#41;)

[//]: # ()
[//]: # (    method unsubscribe&#40;eventType, listener&#41; is)

[//]: # (        listeners.remove&#40;eventType, listener&#41;)

[//]: # ()
[//]: # (    method notify&#40;eventType, data&#41; is)

[//]: # (        foreach &#40;listener in listeners.of&#40;eventType&#41;&#41; do)

[//]: # (            listener.update&#40;data&#41;)

[//]: # (            )
[//]: # (class Editor is)

[//]: # (    public field events: EventManager)

[//]: # (    private field file: File)

[//]: # ()
[//]: # (    constructor Editor&#40;&#41; is)

[//]: # (        events = new EventManager&#40;&#41;)

[//]: # ()
[//]: # (    // Methods of business logic can notify subscribers about)

[//]: # (    // changes.)

[//]: # (    method openFile&#40;path&#41; is)

[//]: # (        this.file = new File&#40;path&#41;)

[//]: # (        events.notify&#40;"open", file.name&#41;)

[//]: # ()
[//]: # (    method saveFile&#40;&#41; is)

[//]: # (        file.write&#40;&#41;)

[//]: # (        events.notify&#40;"save", file.name&#41;)

[//]: # (        )
[//]: # (interface EventListener is)

[//]: # (    method update&#40;filename&#41;)

[//]: # (    )
[//]: # (class LoggingListener implements EventListener is)

[//]: # (    private field log: File)

[//]: # (    private field message: string)

[//]: # ()
[//]: # (    constructor LoggingListener&#40;log_filename, message&#41; is)

[//]: # (        this.log = new File&#40;log_filename&#41;)

[//]: # (        this.message = message)

[//]: # ()
[//]: # (    method update&#40;filename&#41; is)

[//]: # (        log.write&#40;replace&#40;'%s',filename,message&#41;&#41;)

[//]: # (        )
[//]: # (class EmailAlertsListener implements EventListener is)

[//]: # (    private field email: string)

[//]: # (    private field message: string)

[//]: # ()
[//]: # (    constructor EmailAlertsListener&#40;email, message&#41; is)

[//]: # (        this.email = email)

[//]: # (        this.message = message)

[//]: # ()
[//]: # (    method update&#40;filename&#41; is)

[//]: # (        system.email&#40;email, replace&#40;'%s',filename,message&#41;&#41;)

[//]: # (        )
[//]: # (class Application is)

[//]: # (    method config&#40;&#41; is)

[//]: # (        editor = new Editor&#40;&#41;)

[//]: # ()
[//]: # (        logger = new LoggingListener&#40;)

[//]: # (            "/path/to/log.txt",)

[//]: # (            "Someone has opened the file: %s"&#41;)

[//]: # (        editor.events.subscribe&#40;"open", logger&#41;)

[//]: # ()
[//]: # (        emailAlerts = new EmailAlertsListener&#40;)

[//]: # (            "admin@example.com",)

[//]: # (            "Someone has changed the file: %s"&#41;)

[//]: # (        editor.events.subscribe&#40;"save", emailAlerts&#41;)

[//]: # ()
[//]: # (```)

[//]: # ()
[//]: # (## Applicability)

[//]: # ()
[//]: # (* Use the Observer pattern when changes to the state of one object may require changing other objects, and the actual set of objects is unknown beforehand or changes dynamically.)

[//]: # ()
[//]: # (*  Use the pattern when some objects in your app must observe others, but only for a limited time or in specific cases.)


## Pros and Cons

## Pros
* *Open/Closed Principle.* You can introduce new subscriber classes without having to change the publisher’s code (and vice versa if there’s a publisher interface).
* You can establish relations between objects at runtime.

## Cons
* Subscribers are notified in random order.
