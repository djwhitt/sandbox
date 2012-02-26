class MatchStrategy
  attr_accessor :solution
  
  def initialize(solution)
    @solution = solution
  end

  def max_fitness
    solution.length
  end
  
  def call(individual)
    fitness = 0
    individual.genes.each_with_index do |gene, i|
      if solution[i] == individual.genes[i]
        fitness += 1
      end
    end
    fitness
  end
end
