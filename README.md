# ordered_subscribe_dialogs_example
Ordered subscribe dialogs example

The main issue - how to display dialogs according specific condition, and easily modify(add/remove) dialogs in the future.
To resolve this issue I used Chain of Responsibility pattern.

You coud find some gaps and things which work not perfect - it's ok :) this is some kind of PoC ;)

# How to use
- Build and install apk
- Open app
- press on positive/negative button
- close app by clicking back button or force close app(we need to do this because decision show or not the subscribe dialogs proceed when app started in on onCreate() method)
- repeat all previous step until tyou'll be satisfied

If you'll need reset flow - clear the cache of current app :)


# Version 2
In version 2 was added MVVM arch pattern and refactoring
Main part that was refactored:
- email validation
- creation of dialogs
- displaing data on UI
- other small stuff
