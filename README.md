Coding Challege
=================

http://monospacedmonologues.com/post/12118361399/workshop-functional-programming-in-oo-languages

I have a poker hand—seven cards that look like this:

>"4d 7h 8c 8d Td Js Kh"

Not the prettiest cards on the planet, but they’re a lot easier to parse than real ones. You’ll notice they’re sorted by rank. This will make life easier for you.

Here’s your task: tell me if I have a pair in my hand. For the hand above, the pair consists of the eight of clubs and eight of diamonds. I don’t need to know which cards are in the pair though, just whether I have one or not.

When you’re done with determining whether the hand contains a pair, work your way up through each of the poker categories. Wikipedia has an explanation of each one. For example, the hand “2s 5s 7c 7s 8h Js As” contains a flush, which trumps a pair, so the category is flush.

Right. Now to make it interesting. Immutability is key. To this end, I’ve come up with a few rules you should follow.

No mutable types. That means no arrays, or even mutable lists such as the lists bundled with every mainstream programming language. Java’s ArrayList, Ruby’s Array, Python’s list… these are all out of bounds.

Don’t write your own mutable types. Really important. The state of your objects should not change during the lifetime of the object. If you’re developing in Java, this means that all fields should be final. readonly in C#. Everyone else, just don’t do it.

Methods must end by returning. Functions. One thing in, one thing out. Emphasis on the out.

Every single statement (apart from the aforementioned return) must be assignment to new variables only. Again, mark them as final if you can.

No conditions or loops. Essentially, no if, for or while blocks. These encourage mutation. The one exception is the ternary condition: condition ? true_case : false_case in Java, C#, Ruby and JavaScript (and I’m sure many more). Python’s equivalent is true_case if condition else false_case, which I always thought was backwards, but that shouldn’t stop you from using it.
