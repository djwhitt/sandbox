require 'helper'

require 'individual'
require 'bit_flip_mutation'

class TestBitFlipMutation < MiniTest::Unit::TestCase
  def test_mutate
    mutation_strategy = BitFlipMutation.new(:rate => 1.0)
    individual = Individual.new
    individual[0] = 0
    mutation_strategy.call(individual)
    assert_equal 1, individual[0]
  end
end
