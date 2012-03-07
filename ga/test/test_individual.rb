require 'individual'

class TestIndividual < MiniTest::Unit::TestCase
  def test_initialize_with_default_length
    individual = Individual.new
    assert_equal Individual::DEFAULT_GENE_LENGTH, individual.length
  end

  def test_initialize_with_custom_length
    custom_length = 32
    individual = Individual.new(:length => custom_length)
    assert_equal custom_length, individual.length
  end

  def test_generate
    individual = Individual.new
    individual.generate
    assert_equal Individual::DEFAULT_GENE_LENGTH, individual.genes.length
  end

  def test_fitness
    strategy = MiniTest::Mock.new
    individual = Individual.new(:fitness_strategy => strategy)
    strategy.expect(:call, :fitness, [individual])
    assert_equal :fitness, individual.fitness
    strategy.verify
  end

  def test_comparison
    strategy1 = MiniTest::Mock.new
    individual1 = Individual.new(:fitness_strategy => strategy1)
    strategy1.expect(:call, 0, [individual1])

    strategy2 = MiniTest::Mock.new
    individual2 = Individual.new(:fitness_strategy => strategy2)
    strategy2.expect(:call, 1, [individual2])

    assert_operator individual1, :<, individual2
    assert_operator individual2, :>, individual1
  end

  def test_each
    individual = Individual.generate(:length => 5)
    i = 0
    individual.each do |gene|
      refute_nil gene
      i += 1
    end
    assert_equal i, individual.length
  end
end
