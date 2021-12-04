require_relative 'rating_calculator'
require_relative 'bitwise_utils'

# Solution for day 2 Advent of Code 2021
class Day3
  def initialize
    @rating_calculator = RatingCalculator.new
  end

  def solve_first(input)
    values = read_input(input)
    size = number_of_bits(values)

    gamma = @rating_calculator.calculate_gamma_rate(values, size)
    epsilon = @rating_calculator.calculate_epsilon_rate(gamma, size)

    calculate_power_consumption(gamma, epsilon)
  end

  def solve_second(input)
    values = read_input(input)
    size = number_of_bits(values)

    oxygen = @rating_calculator.calculate_oxygen_rating(values, size)
    co2 = @rating_calculator.calculate_co2_rating(values, size)

    calculate_life_support_rating(oxygen, co2)
  end

  private

  def read_input(input)
    input
      .split(/\n+/)
      .reject(&:empty?)
  end

  def calculate_power_consumption(gamma, epsilon)
    gamma * epsilon
  end

  def calculate_life_support_rating(oxygen, co2)
    oxygen * co2
  end
end
