class BitFlipMutation
  DEFAULT_RATE = 0.015

  attr_accessor :rate

  def initialize(opts={})
    @rate = opts.fetch(:rate) { DEFAULT_RATE }
  end

  def call(individual)
    individual.each_with_index do |gene, i|
      if rand <= @rate
        individual[i] = gene ^ 1
      end
    end
  end
end
