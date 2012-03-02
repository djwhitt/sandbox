require 'individual'
require 'population'
require 'null_object'

class Algorithm
  UNIFORM_RATE = 0.5
  MUTATION_RATE = 0.015
  TOURNAMENT_SIZE = 5
  ELITISM = true

  def self.evolve(population)
    new_population = Population.new(population.size, population.fitness_strategy, false)

    if ELITISM
      new_population[0] = population.fittest
    end

    offset = 0
    if ELITISM
      offset = 1
    end

    (offset..population.size).each do |i|
      individual1 = tournament_selection(population)
      individual2 = tournament_selection(population)
      new_individual = crossover(individual1, individual2)
      new_population[i] = new_individual
    end

    (offset..new_population.size).each do |i|
      mutate(new_population[i])
    end

    new_population
  end

  def self.crossover(individual1, individual2)
    new_individual = individual1.dup
    individual1.length.times do |i|
      if rand <= UNIFORM_RATE
        new_individual.genes[i] = individual1.genes[i]
      else
        new_individual.genes[i] = individual2.genes[i]
      end
    end
    new_individual
  end

  def self.mutate(individual)
    individual.length.times do |i|
      if rand <= MUTATION_RATE
        individual[i] = rand(2)
      end
    end
  end

  def self.tournament_selection(population)
    tournament = Population.new(TOURNAMENT_SIZE, NullObject.new, false)
    TOURNAMENT_SIZE.times do |i|
      tournament[i] = population[rand(population.size)]
    end
    tournament.fittest
  end
end
