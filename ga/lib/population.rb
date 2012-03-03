require 'individual'
require 'null_fitness_strategy'

class Population
  attr_accessor :size, :individuals, :fitness_strategy

  def initialize(opts={})
    @size = opts.fetch(:size)
    @fitness_strategy = opts.fetch(:fitness_strategy) { NullFitnessStrategy.new }
    generate = opts.fetch(:generate) { false }
    
    @individuals = []
    size.times do |i|
      individual = Individual.new(:fitness_strategy => @fitness_strategy)
      individual.generate if generate      
      @individuals[i] = individual
    end
  end

  def self.generate(opts={})
    self.new(opts.merge(:generate => true))
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
