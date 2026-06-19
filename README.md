# Avaj Launcher
## Overview

Avaj Launcher is a Java simulation project focused on Object-Oriented Programming principles and design patterns. The program simulates different types of aircraft reacting to changing weather conditions provided by a weather tower.

Aircraft register themselves to the weather tower and receive weather updates throughout the simulation until they land.

## Features
-Weather-driven aircraft simulation
-Multiple aircraft types:
    -Baloon
    -Helicopter
    -JetPlane
-Aircraft registration and deregistration through a Weather Tower
-Scenario file validation
-Automatic simulation report generation
-Use of several design patterns:
    -Singleton
    -Factory
    -Observer

## Compilation

From the project root directory:

find src -name "*.java" > sources.txt
javac @sources.txt

Or:

javac $(find src -name "*.java")

## Usage

Run the simulator with a scenario file:

java src.simulator.Simulator scenario.txt

### Arguments

The program expects exactly one argument:

<scenario_file>.txt

## Scenario File Format

The first line specifies the number of weather simulations to execute.

Each following line defines an aircraft:

TYPE NAME LONGITUDE LATITUDE HEIGHT

### Example

5
Baloon B1 2 3 20
JetPlane J1 23 44 80
Helicopter H1 10 20 30

### Parameters

Field	Description
TYPE	Baloon, JetPlane, or Helicopter
NAME	Aircraft name
LONGITUDE	Positive integer
LATITUDE	Positive integer
HEIGHT	Integer between 0 and 100

## Simulation Flow

The scenario file is validated and parsed.
A WeatherTower is created.
Aircraft are instantiated through the AircraftFactory.
Each aircraft registers itself to the weather tower.
The weather tower updates all registered aircraft during each simulation cycle.
Aircraft modify their coordinates according to the current weather.
Aircraft landing events trigger automatic deregistration.
All events are written to simulation.txt.

## Weather Conditions

The WeatherProvider can return one of four weather conditions:

SUN
RAIN
FOG
SNOW

Each aircraft reacts differently.

### Baloon

Weather     Effect
SUN         Longitude +2, Height +5
RAIN        Height -5
FOG         Height -3
SNOW        Height -15

### Helicopter

Weather     Effect
SUN         Longitude +10, Height +2
RAIN        Longitude +5
FOG         Longitude +1
SNOW        Height -12

### JetPlane

Weather     Effect
SUN         Latitude +10, Height +2
RAIN        Latitude +5
FOG         Latitude +1
SNOW        Height -7

### Height Limit

Aircraft altitude is capped at: 100

## Landing

When an aircraft's height becomes lower than zero:
-The aircraft lands.
-It unregisters from the weather tower.
-A landing message is added to the simulation report.

## Output

At the end of the simulation, a file named simulation.txt is generated.

The file contains:
-Tower registration messages
-Tower deregistration messages
-Aircraft weather reactions
-Landing notifications

## Error Handling

The application validates:

-File existence
-Read permissions
-Correct file format
-Positive iteration count
-Valid coordinates
-Height range (0–100)
-Supported aircraft types

Custom exceptions are handled through InvalidFileInformation.

## Design Patterns

### Singleton

Used by:
- WeatherProvider
- AircraftFactory
- FileHandler

Ensures only one instance exists during the application's lifecycle.

### Factory

Implemented by:

AircraftFactory

Responsible for creating aircraft objects based on their type.

### Observer

Participants:

Subject: Tower
Observers: Flyable aircraft

The weather tower notifies all registered aircraft whenever weather conditions change.

## Output Example

Tower says: Baloon#B1(1) registered to weather tower.
Baloon#B1(1): Let's enjoy the good weather and take some pics.
Baloon#B1(1): Damn you rain! You messed up my balloon.
Baloon#B1(1) landing.
Tower says: Baloon#B1(1) unregistered from weather tower.
