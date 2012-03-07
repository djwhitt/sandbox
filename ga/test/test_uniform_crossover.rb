require 'individual'
require 'uniform_crossover'

class TestUniformCrossover < MiniTest::Unit::TestCase
  def test_crossover
    crossover_strategy = UniformCrossover.new(:rate => 1)
    individual1 = Individual.new
    individual2 = Individual.new
    individual1[0] = 0
    individual2[0] = 1
    new_individual = crossover_strategy.call(individual1, individual2)
    assert_equal individual1, new_individual
  end
end
