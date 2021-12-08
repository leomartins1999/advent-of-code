require_relative 'single_entry_decoder'

# Number of segments mapped to possible values:
# | size | number(s) |
# |  2   |     1     |
# |  3   |     7     |
# |  4   |     4     |
# |  5   |   2,3,5   |
# |  6   |   0,6,9   |
# |  7   |     8     |

NUMBER_OF_SEGMENTS_FOR_1 = 2.freeze
NUMBER_OF_SEGMENTS_FOR_4 = 4.freeze
NUMBER_OF_SEGMENTS_FOR_7 = 3.freeze
NUMBER_OF_SEGMENTS_FOR_8 = 7.freeze

OUTPUT_SIZE_TO_NUMBER_DECODER = {
    NUMBER_OF_SEGMENTS_FOR_1 => 1,
    NUMBER_OF_SEGMENTS_FOR_4 => 4,
    NUMBER_OF_SEGMENTS_FOR_7 => 7,
    NUMBER_OF_SEGMENTS_FOR_8 => 8
}.freeze

NUMBER_FILTER = [1, 4, 7, 8].freeze

class EntriesDecoder

    def count_digits_appearances(entries, filter = NUMBER_FILTER)
        entries
            .map(&:outputs)
            .map { |encoded_output| decode_output(encoded_output) }
            .map { |decoded_output| decoded_output.select { |v| filter.include?(v)} }
            .map { |filtered_output| filtered_output.size }
            .sum
    end

    def decode_and_sum_outputs(entries)
        entries
            .map { |entry| SingleEntryDecoder.new(entry) }
            .map(&:decode_outputs)
            .map { |output| output.join('') }
            .map { |output| output.to_i }
            .sum
    end

    private

    # decodes directly known outputs (because of their string size)
    # outputs decoded indirectly are not handled by this method (and have value -1)
    def decode_output(output)
        output.map { |o| OUTPUT_SIZE_TO_NUMBER_DECODER.fetch(o.size, -1) }
    end

end
