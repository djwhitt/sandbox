require 'helper'

require 'individual'
require 'null_object'

class TestIndividual < MiniTest::Unit::TestCase
  def test_initialize_with_default_length
    individual = Individual.new
    assert_equal Individual::DEFAULT_GENE_LENGTH, individual.length
  end

  def test_initialize_with_custom_length
    custom_length = 32
    individual = Individual.new
    individual.length = custom_length
    assert_equal custom_length, individual.length
  end

  def test_generate
    individual = Individual.new
    individual.generate
    assert_equal Individual::DEFAULT_GENE_LENGTH, individual.genes.length
  end

  def test_fitness
    strategy = MiniTest::Mock.new
    individual = Individual.new
    individual.fitness_strategy = strategy
    strategy.expect(:call, :fitness, [individual])
    assert_equal :fitness, individual.fitness
    strategy.verify
  end
end
