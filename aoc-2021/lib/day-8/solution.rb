require_relative 'entry_parser'
require_relative 'entries_decoder'

# Solution for day 8 of Advent of Code 2021
class Day8

  def initialize
    @parser = EntryParser.new
    @decoder = EntriesDecoder.new
  end

  def solve_first(input)
    entries = @parser.parse_input(input)

    @decoder.count_digits_appearances(entries)
  end

  def solve_second(input)
    entries = @parser.parse_input(input)

    @decoder.decode_and_sum_outputs(entries)
  end
end
