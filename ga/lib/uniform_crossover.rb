class UniformCrossover
  DEFAULT_RATE = 0.5

  attr_accessor :rate
  
  def initialize(opts={})
    @rate = opts.fetch(:rate) { DEFAULT_RATE }
  end

  def call(individual1, individual2)
    new_individual = individual1.dup
    individual1.each_with_index do |gene, i|
      if rand <= @rate
        new_individual[i] = individual1[i]
      else
        new_individual[i] = individual2[i]
      end
    end
    new_individual
  end
end
