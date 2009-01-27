#!/usr/bin/perl

use warnings;
use strict;

local $/;
$_ = <>;

local @_;

for my $match (/<element[^>]*>(.*?)<\/element>/sig) {
    my @tags = $match =~ /<([a-z_]+)>/sig;
    local %_ = map {$match =~ /<$_>(.*?)<\/$_>/si; $_ => $1} @tags;
   
    next unless $_{type} =~ /class/i;
 
    $_{panel_attributes} =~ /^(.*)\n/;
    my $name = $1;
    
    $_{panel_attributes} =~ /--\n(.*)/s;
    my $resp = $1;
    $resp =~ s/\s+/ /sg;
    
    push @_, {
        name => $name,
        responsibilities => $resp,
    };
}

printf("Name: %s\nResponsibilities: %s\n\n",
    $_->{name},
    $_->{responsibilities})
    for sort {$a->{name} cmp $b->{name}} @_;
