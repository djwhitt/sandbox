require 'individual'

class Population
  attr_accessor :size, :individuals, :fitness_strategy

  def initialize(size, fitness_strategy, generate=true)
    @size = size
    @individuals = []
    @fitness_strategy = fitness_strategy
    size.times do |i|
      individual = Individual.new(:fitness_strategy => @fitness_strategy)
      individual.generate if generate
      @individuals[i] = individual
    end
  end

  def fittest
    @individuals.max_by {|i| i.fitness }
  end

  def [](i)
    @individuals[i]
  end

  def []=(i, individual)
    @fitness = nil
    @individuals[i] = individual
  end
end
