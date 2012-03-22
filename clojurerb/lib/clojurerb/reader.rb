require 'parslet'

module ClojureRB
  class Reader < Parslet::Parser
    rule(:integer) { match['0-9'].repeat(1).as(:integer) }
    rule(:_nil) { str('nil').as(:nil) }
    rule(:form) { integer | _nil }
    
    root(:form)
  end
end
