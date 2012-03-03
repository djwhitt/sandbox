class Individual
  include Comparable
  include Enumerable
  
  DEFAULT_GENE_LENGTH = 64

  attr_accessor :genes, :length, :fitness_strategy, :crossover_strategy

  def initialize(opts={})
    @fitness_strategy = opts[:fitness_strategy]
    @crossover_strategy = opts[:crossover_strategy]
    @length = opts.fetch(:length) { DEFAULT_GENE_LENGTH }
    @fitness = nil
    @genes = []
  end

  def self.generate(opts={})
    self.new(opts).generate
  end

  def initialize_copy(source)
    super
    @genes = @genes.dup
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
  
  def <=>(o)
    self.class == o.class ? fitness <=> o.fitness : nil
  end

  def [](i)
    @genes[i]
  end

  def []=(i, gene)
    @fitness = nil # reset cached fitness    
    @genes[i] = gene
  end

  def each(&block)
    @genes.each(&block)
  end

  def crossover(other_individual)
    @crossover_strategy.call(self, other_individual)
  end
  alias_method :+, :crossover
end
