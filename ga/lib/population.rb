require 'individual'

class Population
  attr_accessor :individuals, :fitness_strategy, :crossover_strategy

  def initialize(opts={})
    size = opts.fetch(:size)
    @fitness_strategy = opts[:fitness_strategy]
    @crossover_strategy = opts[:crossover_strategy]
    generate = opts.fetch(:generate) { false }
    
    @individuals = Array.new(size)
    size.times do |i|
      individual = Individual.new(:fitness_strategy => @fitness_strategy,
                                  :crossover_strategy => @crossover_strategy)
      individual.generate if generate      
      @individuals[i] = individual
    end
  end

  def self.generate(opts={})
    opts = opts.merge(:generate => true)
    self.new(opts)
  end

  def fittest
    @individuals.max
  end

  def [](i)
    @individuals[i]
  end

  def []=(i, individual)
    @individuals[i] = individual
  end

  def size
    @individuals.size
  end

  def initialize_copy(source)
    super
    @individuals = @individuals.dup
    @individuals.size.times do |i|
      @individuals[i] = @individuals[i].dup
    end
  end
end
