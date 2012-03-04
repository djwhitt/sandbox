require 'individual'
require 'population'

class Algorithm
  MUTATION_RATE = 0.015
  TOURNAMENT_SIZE = 5
  ELITISM = true

  def self.evolve(population)
    new_population = population.dup

    if ELITISM
      new_population[0] = population.fittest
    end

    offset = 0
    if ELITISM
      offset = 1
    end

    (offset...population.size).each do |i|
      individual1 = tournament_selection(population)
      individual2 = tournament_selection(population)
      new_individual = individual1 + individual2
      new_population[i] = new_individual
    end

    (offset...new_population.size).each do |i|
      new_population[i].mutate
    end

    new_population
  end

  def self.tournament_selection(population)
    tournament = Population.new(:size => TOURNAMENT_SIZE)
    TOURNAMENT_SIZE.times do |i|
      tournament[i] = population[rand(population.size)]
    end
    tournament.fittest
  end
end
