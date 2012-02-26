require 'helper'
require 'individual'

class TestIndividual < MiniTest::Unit::TestCase
  def setup
    @individual = Individual.new
  end
  
  def test_initialize
    assert_equal [], @individual.genes
    assert_equal Individual::DEFAULT_GENE_LENGTH, @individual.length
  end

  def test_generate
    @individual.generate
    assert_equal @individual.length, @individual.genes.size
  end
end
