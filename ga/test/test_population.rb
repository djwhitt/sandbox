require 'helper'
require 'population'

class TestIndividual < MiniTest::Unit::TestCase
  def setup
    @size = 10
    @population = Population.new(10)
  end

  def test_initialize
    population = Population.new(10)
    assert_equal 10, population.individuals.size 
  end
end
