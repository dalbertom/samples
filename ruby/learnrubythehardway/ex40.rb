#!/usr/bin/env ruby -w

# Exercise 40: Modules, Classes, and Objects

class MyStuff
  def initialize()
    @tangerine = "And now a thousand years between"
  end

  attr_reader :tangerine

  def apple()
    puts "I AM CLASSY APPLES!"
  end
end

fruit = MyStuff.new()
puts fruit.tangerine
fruit.apple()
