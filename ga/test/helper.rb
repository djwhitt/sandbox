require 'rubygems'
require 'isolate/now'
gem 'minitest'
require 'minitest/autorun'
require 'minitest/mock'

$:.unshift File.dirname(__FILE__) + '/../lib'
