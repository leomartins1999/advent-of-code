START_DELIMITERS = ['(', '[', '{', '<'].freeze
END_DELIMITERS = [')', ']', '}', '>'].freeze

DELIMITERS_MAPPER = (0..START_DELIMITERS.size - 1)
                    .map { |idx| { START_DELIMITERS[idx] => END_DELIMITERS[idx] } }
                    .reduce({}) { |acc, map| acc.merge(map) }
                    .freeze

POINTS_MAPPER = {
  ')' => 3,
  ']' => 57,
  '}' => 1197,
  '>' => 25_137
}.freeze

class LineInterpreter

  def get_syntax_score(lines)
    lines
      .map { |l| get_syntax_score_for_line(l) }
      .sum
  end

  def get_autocomplete_score(lines)
    scores = lines
             .reject { |l| is_line_corrupted?(l) }
             .map { |l| get_missing_characters_for_line(l) }
             .map { |l| get_score_for_line(l) }
             .sort

    scores[scores.length / 2]
  end

  private

  def get_syntax_score_for_line(line)
    stack = []

    line.each do |c|
      if START_DELIMITERS.include?(c)
        stack << c
      elsif END_DELIMITERS.include?(c)
        return POINTS_MAPPER[c] if DELIMITERS_MAPPER[stack.pop] != c
      end
    end

    0
  end

  def is_line_corrupted?(line)
    get_syntax_score_for_line(line) > 0
  end

  def get_missing_characters_for_line(line)
    stack = []

    line.each do |c|
      if START_DELIMITERS.include?(c)
        stack << c
      elsif END_DELIMITERS.include?(c)
        stack.pop
      end
    end

    stack
      .reverse
      .map { |c| DELIMITERS_MAPPER[c] }
  end

  def get_score_for_line(line)
    line.reduce(0) do |acc, v|
      acc * 5 + (END_DELIMITERS.index(v) + 1)
    end
  end
  
end
