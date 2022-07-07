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

Add a subscription mechanism to the publisher class so individual objects can subscribe to or unsubscribe from
This mechanism consists of:
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

## Pseudocode
In this example, the Observer pattern lets the text editor object notify other service objects about changes in its state

<figure>
  <img src="./images/obs_example.png">
  <figcaption>
      Notifying objects about events that happen to other objects.
  </figcaption>
</figure>

```
class EventManager is
    private field listeners: hash map of event types and listeners

    method subscribe(eventType, listener) is
        listeners.add(eventType, listener)

    method unsubscribe(eventType, listener) is
        listeners.remove(eventType, listener)

    method notify(eventType, data) is
        foreach (listener in listeners.of(eventType)) do
            listener.update(data)
            
class Editor is
    public field events: EventManager
    private field file: File

    constructor Editor() is
        events = new EventManager()

    // Methods of business logic can notify subscribers about
    // changes.
    method openFile(path) is
        this.file = new File(path)
        events.notify("open", file.name)

    method saveFile() is
        file.write()
        events.notify("save", file.name)
        
interface EventListener is
    method update(filename)
    
class LoggingListener implements EventListener is
    private field log: File
    private field message: string

    constructor LoggingListener(log_filename, message) is
        this.log = new File(log_filename)
        this.message = message

    method update(filename) is
        log.write(replace('%s',filename,message))
        
class EmailAlertsListener implements EventListener is
    private field email: string
    private field message: string

    constructor EmailAlertsListener(email, message) is
        this.email = email
        this.message = message

    method update(filename) is
        system.email(email, replace('%s',filename,message))
        
class Application is
    method config() is
        editor = new Editor()

        logger = new LoggingListener(
            "/path/to/log.txt",
            "Someone has opened the file: %s")
        editor.events.subscribe("open", logger)

        emailAlerts = new EmailAlertsListener(
            "admin@example.com",
            "Someone has changed the file: %s")
        editor.events.subscribe("save", emailAlerts)

```

## Applicability

* Use the Observer pattern when changes to the state of one object may require changing other objects, and the actual set of objects is unknown beforehand or changes dynamically.

*  Use the pattern when some objects in your app must observe others, but only for a limited time or in specific cases.


## Pros and Cons

## Pros
* *Open/Closed Principle.* You can introduce new subscriber classes without having to change the publisher’s code (and vice versa if there’s a publisher interface).
* You can establish relations between objects at runtime.

## Cons
* Subscribers are notified in random order.
