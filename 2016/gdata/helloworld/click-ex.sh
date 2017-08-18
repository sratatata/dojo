class Config(object):

    def __init__(self):
        self.verbose = False

pass_config = click.make_pass_decorator(Config, ensure=True)

@click.group()
@click.option('--verbose', is_flag=True)
@click.option('--home-directory', type=click.Path())
@pass_config
def cli(config, verbose, home_directory):
    config.verbose = verbose
    if home_directory is None:
        home_directory = '.'
    config.home_directory = home_directory

@cli.command()
@click.option('--string', default='World')
@click.option('--repeat', default=1,help='How manu')
@click.argument('out', type=click.File('w'), default='-', required=False)
@pass_config
def say(config, string, repeat, out):
    """This is example application"""
    if config.verbose:
        click.echo('We are in verbose!')
    click.echo('Home directory is %s'%config.home_directory)
    for x in xrange(repeat):
        click.echo('Hello %s!' % string, file=out)

@cli.command()
@pass_config
def ask(config):
    """Ask for for"""
    click.echo("shit!")


if __name__ == '__main__':
    cli()