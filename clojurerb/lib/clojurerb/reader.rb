require 'parlet'

module ClojureRB
  class Reader < Parlet::Parser
    root(:integer)
    rule(:integer) { match['0-9'] }
  end
end
