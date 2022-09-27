# My Personal Project: Fridge & Pantry

## Overview

**Fridge & Pantry** is an application designed to keep track of a household's or individual's
food and beverage inventory over time. It will allow users to input and categorize different
items into their kitchen, and allow users to also view details about the item such as expiration date
type, and location. Users will also be allowed to remove items and potentially add (and remove) items to 
a grocery list if they choose to.

This application is intended for users of all demographics who find managing their food inventory
to be challenging.

Quite often, things in my own pantry and fridge have gone past their expiration date without
me noticing. When buying groceries, I also often forget what things I already have at home.
Having a simple system that allows me to check my inventory when I need to will allow me to
remember to consume or use things before they expire, and prevent buying groceries I already
have or don't need.

## User Stories

- As a user, I want to be able to add multiple food items to my kitchen.
- As a user, I want to be able to remove a food item from my kitchen.
- As a user, I want to be able to select a food item and view it in detail.
- As a user, I want to be able to view a list of food items in my kitchen.
- As a user, I want to be able to save my kitchen list to file.
- As a user, I want to be able to load my kitchen list from file.
- As a user, I want to be able to add multiple food items to my grocery list.
- As a user, I want to be able to remove a food item from my grocery list.
- As a user, I want to be able to view a list of food items in my grocery list.
- As a user, I want to be able to save my grocery list to file.
- As a user, I want to be able to load my grocery list from file.

## Instructions for User

- You must click "Load Existing Kitchen" in the kitchen menu before modifying the kitchen list.
- You can add and remove items by clicking on "View Kitchen" in the main menu, then "Modify List"
in the kitchen menu. You will then be prompted to fill out the inputs following the directions, and
have a choice of clicking "Add Item to Kitchen" or "Remove Item from Kitchen".
- You can view a list of the items in the kitchen by clicking on "View Kitchen" in the main menu, then
"View Kitchen Inventory" in the kitchen menu.
- You can save the state of the kitchen of my application by clicking on "View Kitchen" in
the main menu, then "Save Kitchen" in the kitchen menu.
- You can reload the state of the kitchen of my application by clicking on "View Kitchen" in
the main menu, then "Load Existing Kitchen" in the kitchen menu.


- You must click "Load Existing List" in the grocery menu before modifying the grocery list.
- You can add and remove items by clicking on "View Grocery List" in the main menu, then "Modify Grocery List"
in the grocery menu. You will then be prompted to fill out the input following the directions, and
have a choice of clicking "Add Item to List" or "Remove Item from List".
- You can view a list of the items in the list by clicking on "View Grocery List" in the main menu, then
"View Grocery List" in the grocery menu.
- You can save the state of the grocery list of my application by clicking on "View Grocery List" in
the main menu, then "Save Grocery List" in the grocery menu.
- You can reload the state of the grocery list of my application by clicking on "View Grocery List" in
the main menu, then "Load Existing List" in the grocery menu.

## Reflections

- The first change I would make is to create an interface
or abstract class that both GroceryList and Kitchen could implement
or extend as they are very similar classes.

- The second change is to create an interface or abstract class that
both FoodItem and ItemToBuy could implement or extend as they
are also very similar to one another.

- Lastly, I would create separate GUI classes for the kitchen and the grocery list and have
the main GUI use these classes because currently my file is very
bulky and long. This would improve the cohesion overall.
