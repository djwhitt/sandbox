require 'helper'

require 'ostruct'

require 'null_object'
require 'population'

class TestIndividual < MiniTest::Unit::TestCase
  def test_initialize
    population = Population.new(10, NullObject.new)
    assert_equal 10, population.individuals.size 
  end

  def test_fittest
    population = Population.new(2, NullObject.new)
    
    individual_0 = OpenStruct.new(:fitness => 0)
    population.individuals[0] = individual_0
    
    individual_1 = OpenStruct.new(:fitness => 1)
    population.individuals[1] = individual_1

    assert_equal individual_1, population.fittest
  end
end
