require 'null_fitness_strategy'

class Individual
  DEFAULT_GENE_LENGTH = 64

  attr_accessor :genes, :length, :fitness_strategy

  def initialize(opts={})
    @fitness_strategy = opts.fetch(:fitness_strategy) { NullFitnessStrategy.new }
    @length = opts.fetch(:length) { DEFAULT_GENE_LENGTH }
    @fitness = nil
    @genes = []
  end

  def self.generate(opts={})
    self.new(opts).generate
  end

  def generate
    @length.times do |i|
      @genes[i] = rand(2)
    end
    self
  end

  def fitness
    @fitness ||= @fitness_strategy.call(self)
  end

  def [](i)
    @genes[i]
  end

  def []=(i, gene)
    @fitness = nil
    @genes[i] = gene
  end

  def initialize_copy(source)
    super
    @genes = @genes.dup
  end
end
