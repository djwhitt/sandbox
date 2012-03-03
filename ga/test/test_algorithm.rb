require 'helper'

require 'algorithm'
require 'individual'

class TestAlgorithm < MiniTest::Unit::TestCase
  def test_crossover
    individual1 = Individual.generate
    individual2 = Individual.generate
    new_individual = Algorithm.crossover(individual1, individual2)
    refute_nil new_individual
    assert_equal individual1.genes.length, new_individual.genes.length
  end

  def test_mutate
    individual = Individual.generate
    Algorithm.mutate(individual)
  end
end
