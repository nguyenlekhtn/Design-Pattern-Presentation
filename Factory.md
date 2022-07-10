# Factory Method

## Intent
* Is a creational design pattern
* Provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.
<figure>
  <img src="./images/factory-method-en.png">
</figure>


## Problem

<figure>
  <img src="./images/fac_problem1-en.png">
  <figcaption style="font-size: 80%">
      Adding a new class to the program isn’t that simple if the rest of the code is already coupled to existing classes.
  </figcaption>
</figure>

## Solution

Replace direct object construction calls (using the new operator) with calls to a special factory method.  

The objects are still created via the new operator, but it's being called from within the factory method.  


<figure>
  <img src="./images/fac_solution1.png">
  <figcaption style="font-size: 90%">
      Adding a new class to the program isn’t that simple if the rest of the code is already coupled to existing classes.
  </figcaption>
</figure>

Now you can override the factory method in a subclass and change the class of products being created by the method.

Limitation: How do Subclasses return different types of products?
<figure>
  <img src="./images/fac_solution2-en.png">
  <figcaption style="font-size: 90%">
      All products must follow the same interface.
  </figcaption>
</figure>

<figure>
<img src="./images/fac_solution3-en.png">
  <figcaption style="font-size: 90%">
      As long as all product classes implement a common interface, you can pass their objects to the client code without breaking it.
  </figcaption>
</figure>
The code that uses the factory method (client code) doesn't see a different betwen the actual products return by various subclasses.


## Structure

<figure>
  <img src="./images/fac_structure-indexed.png">
</figure>

[//]: # (## Pseudocode)

[//]: # ()
[//]: # (<figure>)

[//]: # (  <img src="./images/fac_example%20&#40;1&#41;.png">)

[//]: # (  <figcaption style="font-size: 90%">)

[//]: # (      The cross-platform dialog example.)

[//]: # (  </figcaption>)

[//]: # (</figure>)

[//]: # (  )
[//]: # (```)

[//]: # (// The creator class declares the factory method that must)

[//]: # (// return an object of a product class. The creator's subclasses)

[//]: # (// usually provide the implementation of this method.)

[//]: # (class Dialog is)

[//]: # (    // The creator may also provide some default implementation)

[//]: # (    // of the factory method.)

[//]: # (    abstract method createButton&#40;&#41;:Button)

[//]: # ()
[//]: # (    // Note that, despite its name, the creator's primary)

[//]: # (    // responsibility isn't creating products. It usually)

[//]: # (    // contains some core business logic that relies on product)

[//]: # (    // objects returned by the factory method. Subclasses can)

[//]: # (    // indirectly change that business logic by overriding the)

[//]: # (    // factory method and returning a different type of product)

[//]: # (    // from it.)

[//]: # (    method render&#40;&#41; is)

[//]: # (        // Call the factory method to create a product object.)

[//]: # (        Button okButton = createButton&#40;&#41;)

[//]: # (        // Now use the product.)

[//]: # (        okButton.onClick&#40;closeDialog&#41;)

[//]: # (        okButton.render&#40;&#41;)

[//]: # ()
[//]: # ()
[//]: # (// Concrete creators override the factory method to change the)

[//]: # (// resulting product's type.)

[//]: # (class WindowsDialog extends Dialog is)

[//]: # (    method createButton&#40;&#41;:Button is)

[//]: # (        return new WindowsButton&#40;&#41;)

[//]: # ()
[//]: # (class WebDialog extends Dialog is)

[//]: # (    method createButton&#40;&#41;:Button is)

[//]: # (        return new HTMLButton&#40;&#41;)

[//]: # ()
[//]: # ()
[//]: # (// The product interface declares the operations that all)

[//]: # (// concrete products must implement.)

[//]: # (interface Button is)

[//]: # (    method render&#40;&#41;)

[//]: # (    method onClick&#40;f&#41;)

[//]: # ()
[//]: # (// Concrete products provide various implementations of the)

[//]: # (// product interface.)

[//]: # (class WindowsButton implements Button is)

[//]: # (    method render&#40;a, b&#41; is)

[//]: # (        // Render a button in Windows style.)

[//]: # (    method onClick&#40;f&#41; is)

[//]: # (        // Bind a native OS click event.)

[//]: # ()
[//]: # (class HTMLButton implements Button is)

[//]: # (    method render&#40;a, b&#41; is)

[//]: # (        // Return an HTML representation of a button.)

[//]: # (    method onClick&#40;f&#41; is)

[//]: # (        // Bind a web browser click event.)

[//]: # ()
[//]: # ()
[//]: # (class Application is)

[//]: # (    field dialog: Dialog)

[//]: # ()
[//]: # (    // The application picks a creator's type depending on the)

[//]: # (    // current configuration or environment settings.)

[//]: # (    method initialize&#40;&#41; is)

[//]: # (        config = readApplicationConfigFile&#40;&#41;)

[//]: # ()
[//]: # (        if &#40;config.OS == "Windows"&#41; then)

[//]: # (            dialog = new WindowsDialog&#40;&#41;)

[//]: # (        else if &#40;config.OS == "Web"&#41; then)

[//]: # (            dialog = new WebDialog&#40;&#41;)

[//]: # (        else)

[//]: # (            throw new Exception&#40;"Error! Unknown operating system."&#41;)

[//]: # ()
[//]: # (    // The client code works with an instance of a concrete)

[//]: # (    // creator, albeit through its base interface. As long as)

[//]: # (    // the client keeps working with the creator via the base)

[//]: # (    // interface, you can pass it any creator's subclass.)

[//]: # (    method main&#40;&#41; is)

[//]: # (        this.initialize&#40;&#41;)

[//]: # (        dialog.render&#40;&#41;)

[//]: # ()
[//]: # (```)

[//]: # (## Applicability)

[//]: # ()
[//]: # (* Use the Factory Method when you don’t know beforehand the exact types and dependencies of the objects your code should work with.)

[//]: # (* Use the Factory Method when you want to provide users of your library or framework with a way to extend its internal components.)

## Pros and Cons
### Pros
* You avoid tight coupling between the creator and the concrete products.
* Single Responsibility Principle. You can move the product creation code into one place in the program, making the code easier to support.
* Open/Closed Principle. You can introduce new types of products into the program without breaking existing client code.

### Cons
* The code may become more complicated since you need to introduce a lot of new subclasses to implement the pattern. The best case scenario is when you’re introducing the pattern into an existing hierarchy of creator classes.
