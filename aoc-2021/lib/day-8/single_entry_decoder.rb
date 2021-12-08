class SingleEntryDecoder

    def initialize(entry)
        @entry = entry

        @pattern_for_1 = get_pattern_with_size(NUMBER_OF_SEGMENTS_FOR_1)
        @pattern_for_4 = get_pattern_with_size(NUMBER_OF_SEGMENTS_FOR_4)
    end

    def decode_outputs
        @entry
            .outputs
            .map { |output| decode_single_output(output) }
    end

    private

    def decode_single_output(output)
        size = output.size

        if OUTPUT_SIZE_TO_NUMBER_DECODER.include?(size)
            OUTPUT_SIZE_TO_NUMBER_DECODER[size]
        elsif size == 5
            decode_for_size_5(output)    
        else
            decode_for_size_6(output)    
        end
    end

    #####
    # String count returns the similarity factor between 2 strings:
    # 'abc'.count('ab') equals 2
    #####

    # possible values: 2,3,5
    def decode_for_size_5(output)
        if @pattern_for_4.count(output) == 2 # the similarity factor between 4 and 2 is 2
            2
        elsif @pattern_for_1.count(output) == 2 # the similarity factor between 1 and 3 is 2
            3
        else # the only remaining possible value is 5
            5
        end
    end

    # possible values: 0,6,9
    def decode_for_size_6(output)
        if @pattern_for_1.count(output) == 1 # the similarity factor between 1 and 6 is 1
            6
        elsif @pattern_for_4.count(output) == 4 # the similarity factor between 4 and 9 is 4
            9
        else # the only remaining possible value is 0
            0
        end
    end

    def get_pattern_with_size(size)
        @entry
            .patterns
            .select { |pat| pat.size == size }
            .first
    end

end
