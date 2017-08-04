# Elevator Code Challenge

Express through diagrams, pseudo code, or actual code, 
a solution that will meet the functional requirements below.

## Scenario:
A new 55 floor skyscraper is being constructed with four standard
elevator shafts, and two express lifts.

Standard Elevator - these are your normal, every day elevator cars, 
with a maximum capacity of 1500lbs.

Express Lifts - these are service elevators for the staff and/or VIP
visitors to the building.  The maximum capacity of an express lift is 
2500lbs.

## Functional Requirements:
- Elevator cars may only move between floors when the doors have been
  securely closed.
- Once the direction of the car has been determined (UP or DOWN), the car
  must travel to the furthest floor requested in the same direction until
  one of the following conditions are true:
  - The car has reached the basement or the top floor.
  - There are no passengers in the car and no further floors are requested.
- An elevator car can only carry the maximum capacity for safety,
  if an individual car's capacity has been exceeded, an alarm should
  sound to instruct the visitors to exit the car.
- Visitors to the building must be able to arrive at their destination
floor 



# Challenge
Write an application to simulate an building and elevator system as 
described by the above requirements to determine the following:
- maximum wait time for a visitor in the lobby to find
  an available elevator car that can take them to their requested
  destination floor
- maximum wait time for an average visitor to return
  to the lobby from any floor

## Given Inputs for Challenge
- Average Visitors
  - arrives at the lobby every 85 seconds.
  - leaves the lobby every 120 seconds.
  - luggage weighs between 150-250lbs.
  - visitor selects a random destination floor from the lobby.
  
- Visiting Times
  - floors 2-8 are attending conferences and remain in the 
  building for 3 hours, after which time, they leave.
  - floors 9-25 are business spaces, visitors to these floors
   remain in the building for 8 hours, after which time, they leave.
 
  
