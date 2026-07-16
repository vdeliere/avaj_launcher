# Avaj Launcher

Java weather simulation for aircraft, built around OOP and classic design patterns.

## Overview

The program simulates aircraft reacting to changing weather conditions provided by a weather tower. Each aircraft registers to the tower, receives updates, and unregisters automatically when it lands.

## Build

From the project root:

```bash
make
```

This compiles the sources into `bin/`.

If you prefer compiling manually:

```bash
javac -d bin $(find src -name "*.java")
```

## Run

Launch the simulator with a scenario file:

```bash
java -cp bin src.simulator.Simulator scenarios/good/good.txt
```

The program expects exactly one argument: the path to a `.txt` scenario file.

## Scenario File

The file format is:

```text
number_of_simulations
TYPE NAME LONGITUDE LATITUDE HEIGHT
TYPE NAME LONGITUDE LATITUDE HEIGHT
...
```

Example:

```text
5
Baloon B1 2 3 20
JetPlane J1 23 44 80
Helicopter H1 10 20 30
```

Parameters:

| Field | Description |
| --- | --- |
| TYPE | `Baloon`, `JetPlane`, or `Helicopter` |
| NAME | Aircraft name |
| LONGITUDE | Positive integer |
| LATITUDE | Positive integer |
| HEIGHT | Integer between 0 and 100 |

## Weather Rules

The weather provider can return four conditions:

```text
SUN
RAIN
FOG
SNOW
```

Aircraft react as follows:

### Baloon

| Weather | Effect |
| --- | --- |
| SUN | Longitude +2, Height +5 |
| RAIN | Height -5 |
| FOG | Height -3 |
| SNOW | Height -15 |

### Helicopter

| Weather | Effect |
| --- | --- |
| SUN | Longitude +10, Height +2 |
| RAIN | Longitude +5 |
| FOG | Longitude +1 |
| SNOW | Height -12 |

### JetPlane

| Weather | Effect |
| --- | --- |
| SUN | Latitude +10, Height +2 |
| RAIN | Latitude +5 |
| FOG | Latitude +1 |
| SNOW | Height -7 |

Aircraft altitude is capped at 100.

## Simulation Flow

- The scenario file is validated and parsed.
- A `WeatherTower` is created.
- Aircraft are instantiated through the `AircraftFactory`.
- Each aircraft registers itself to the weather tower.
- The tower updates every registered aircraft during each simulation cycle.
- Aircraft move according to the current weather.
- When an aircraft's height drops below zero, it lands and unregisters.
- All events are written to `simulation.txt`.

## Error Handling

The application validates:

- File existence
- Read permissions
- Correct file format
- Positive iteration count
- Valid coordinates
- Height range from 0 to 100
- Supported aircraft types

Invalid scenario data is reported through `InvalidFileInformation`.

## Design Patterns

### Singleton

Used by:

- `WeatherProvider`
- `AircraftFactory`
- `FileHandler`

### Factory

`AircraftFactory` creates aircraft objects based on their type.

### Observer

The weather tower is the subject, and flyable aircraft are the observers. The tower notifies all registered aircraft whenever weather changes.

## Output

At the end of the simulation, `simulation.txt` contains tower registration messages, deregistration messages, aircraft weather reactions, and landing notifications.

## Output Example

```text
Tower says: Baloon#B1(1) registered to weather tower.
Baloon#B1(1): Let's enjoy the good weather and take some pics.
Baloon#B1(1): Damn you rain! You messed up my balloon.
Baloon#B1(1) landing.
Tower says: Baloon#B1(1) unregistered from weather tower.
```
