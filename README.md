# BasketSplitter

BasketSplitter is a Java library designed to handle the division of items between available deliveries based on the available configuration. The goal is to minimize the number of deliveries, and the largest delivery should contain as many products as possible.

## Table of Contents
1. [Description](#description)
2. [Installation](#installation)
3. [Configuration](#configuration)
4. [Usage](#usage)


## Description
BasketSplitter contains two main classes: `BasketSplitter` and `JsonReader`. 
- `BasketSplitter`: Handles the division of items between deliveries.
- `JsonReader`: Reads a JSON configuration file and transforms its contents into a map.

## Installation
To install BasketSplitter:
1. Download the BasketSplitter.jar file from the repository.
2. Place the basket-splitter-Dajana_Dominiak_Java_Krakow-jar-with-dependencies.jar file in your project directory.
3. Add the jar file to your project's classpath.

## Configuration
Before running Basket Splitter, configuration file in JSON format must be prepared. It must contain a map in which the keys are product names and the values are lists of possible deliveries of given products. An example configuration file should look like this:
``` html
{
  "ProductOne": ["Delivery 1", "Delivery 2", "Delivery 3"],
  "ProductTwo": ["Delivery 3", "Delivery 4"],
}
```

## Usage
To initialize the `BasketSplitter` object, the absolute path to the configuration file must be used as a parameter. BasketSplitter has only one method - `split()`, which takes as an argument a list of items whose delivery needs to be optimally split. The method returns a map in which the keys are the delivery methods and the values ​​are the products that have been assigned to them.

