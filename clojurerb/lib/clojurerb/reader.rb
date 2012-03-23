require 'parslet'

module ClojureRB
  class Reader < Parslet::Parser
    rule(:integer) { match['0-9'].repeat(1).as(:integer) }
    rule(:nil) { str('nil').as(:nil) }
    rule(:form) { self.nil | integer }
    
    root(:form)
  end
end
