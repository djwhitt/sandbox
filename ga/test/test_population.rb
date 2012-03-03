require 'helper'

require 'population'

class TestIndividual < MiniTest::Unit::TestCase
  def test_initialize
    population = Population.new(:size => 10)
    assert_equal 10, population.individuals.size
    assert_equal 10, population.size
  end

  def test_generate
    Population.new(:size => 10)
  end

  def test_fittest
    population = Population.new(:size => 2)

    population[0] = 1
    population[1] = 2

    assert_equal 2, population.fittest
  end
end
