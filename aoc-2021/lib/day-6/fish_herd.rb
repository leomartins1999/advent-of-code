class FishHerd

    def initialize(fish)
        @population = fish.tally
    end

    def simulate(days)
        (1..days).each do |day|
            pass_day
        end
    end

    def number_of_fish
        @population.values.sum
    end
    
    private

    def pass_day
        hatches = @population.fetch(0, 0)

        (0..5).each do |days|
            @population[days] = @population.fetch(days + 1, 0)
        end

        @population[6] = @population.fetch(7, 0) + hatches
        @population[7] = @population.fetch(8, 0)
        @population[8] = hatches
    end

end
