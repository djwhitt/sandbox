require 'individual'

class Population
  attr_accessor :individuals

  def initialize(size)
    @individuals = []
    size.times do |i|
      individual = Individual.new
      individual.generate
      @individuals[i] = individual
    end
  end
end
