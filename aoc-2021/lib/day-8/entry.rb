class Entry

    attr_reader :patterns
    attr_reader :outputs

    def initialize(patterns, outputs)
        @patterns = patterns
        @outputs = outputs
    end

end
